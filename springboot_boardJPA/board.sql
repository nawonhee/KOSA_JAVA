create table board(
board_no number, --게시글번호
board_title varchar2(50), --게시글제목
board_content varchar2(100), --게시글내용
board_id varchar2(5), --게시글작성자
board_dt date); --게시글 작성일자


alter table board
ADD CONSTRAINT board_no_pk PRIMARY KEY(board_no)
ADD CONSTRAINT board_id_fk FOREIGN KEY(board_id) REFERENCES customer(id);


alter table board
MODIFY board_dt default SYSDATE;

--답글 테이블
DROP TABLE board_reply;

CREATE TABLE board_reply(
reply_no number, --답글번호
reply_board_no number, --게시글번호
reply_parent_no number, --부모답글번호
reply_content varchar2(100),--답글내용
reply_id varchar2(5), --답글작성자
reply_dt date default sysdate); --답글작성일자

ALTER TABLE board_reply
ADD CONSTRAINT reply_no_pk PRIMARY KEY(reply_no)
ADD CONSTRAINT reply_board_no_fk FOREIGN KEY(reply_board_no) REFERENCES board(board_no) ON DELETE CASCADE
ADD CONSTRAINT reply_parent_no_fk FOREIGN KEY(reply_parent_no) REFERENCES board_reply(reply_no) ON DELETE CASCADE
ADD CONSTRAINT reply_id FOREIGN KEY(reply_id) REFERENCES customer(id);

DROP SEQUENCE board_seq;
CREATE SEQUENCE board_seq;
DROP SEQUENCE reply_seq;
CREATE SEQUENCE reply_seq;

--게시글 추가
INSERT INTO board(board_no, board_title, board_content, board_id) VALUES(board_seq.NEXTVAL, '제목1', '내용1', 'ss');
INSERT INTO board(board_no, board_title, board_content, board_id) VALUES(board_seq.NEXTVAL, '제목2', '내용2', 'B');
INSERT INTO board(board_no, board_title, board_content, board_id) VALUES(board_seq.NEXTVAL, '제목3', '내용3', 'A');

SELECT * FROM board;

--답글 추가
--1번글의 답글
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, NULL, '제목1의 답1', 'B');

--1번글의 답글번호(1)의 답글
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, 1, '제목1의 답1의 답1', 'A');
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, 1, '제목1의 답1의 답2', 'ss');

--1번글의 답글번호(2)의 답글
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, 2, '제목1의 답2의 답1', 'C');

SELECT * FROM board_reply;

--계층형 쿼리
SELECT LEVEL, r.*
FROM board_reply r
START WITH reply_parent_no IS NULL --레벨1을 무엇부터 시작할 것인가
CONNECT BY PRIOR reply_no=reply_parent_no; --reply_no를 사용하고 있는 parent_no와 연결, prior는 우선순위

--게시글 목록
SELECT b.*, 
(SELECT COUNT(*) FROM board_reply WHERE reply_board_no=b.board_no) replycnt
FROM board b
ORDER BY board_no DESC;

--게시글 상세
SELECT *
FROM board b LEFT JOIN 
(SELECT level,r1.* FROM board_reply r1 START WITH reply_parent_no IS NULL CONNECT BY PRIOR reply_no =  reply_parent_no 
 ORDER SIBLINGS BY reply_no DESC)r
ON b.board_no = r.reply_board_no
WHERE board_no = 1;
