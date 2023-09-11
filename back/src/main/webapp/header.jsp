<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
*{
	box-sizing: border-box;
 }
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
            
            function ajaxHandler(method, u, target){
                console.log(u)
                
                // $.ajax({
                //     url: u,
                //     method: method,
                //     success: (responseText)=>{
                //         target.html(responseText)
                //     },
                //     error: ()=>{
                //         alert('응답실패')
                //     }
                // })
                if(method == 'GET'){
                    target.load(u, function( response, status, xhr){
                        if(status=="error"){
                            alert(xhr.status+xhr.statusText)
                        }
                    })
                }
            }


            //$(document).ready()
            $(() => {
                //DOM트리에서 section 객체 찾기
                const sectionObj = document.querySelector('section')
                const $sectionObj = $('section')

                console.log("---자바스크립트 객체---")
                console.log(sectionObj)

                console.log("---jQuery 객체---")
                console.log($sectionObj)
                console.log(sectionObj===$sectionObj)
                console.log(sectionObj===$sectionObj.get(0))

                //DOM트리에서 nav>ul>li>a객체들 찾기
                const menus = document.querySelectorAll('nav>ul>li>a')
                const $menus = $('nav>ul>li>a')

                
                $menus.click((e)=>{
                    //alert('메뉴클릭됨')
                    console.log(e.target.className)
                    //menu
                    switch(e.target.className){
                        case 'login':
                            ajaxHandler('GET', "./login.html", $sectionObj)
                            break
                        case 'signup':
                            ajaxHandler('GET','./signup.html', $sectionObj)
                            break
                        case 'logout':break
                        case 'productlist':
                            //ajaxHandler('GET','./productlist.html', $sectionObj)
                            ajaxHandler('GET','./productlist', $sectionObj)
                            break
                        case 'cartlist':break
                        case 'orderlist':break

                    }
                    e.preventDefault()
                })
            })
</script>

<header>
	<img src="./images/logo.png" alt = "로고"/>
</header>
<nav>
	<ul>
       <li><a href="#" class ="login">로그인</a></li>
       <li><a href="#" class = "signup">가입</a></li>
       <li><a href="#" class = "logout">로그아웃</a></li>
       <li><a href="#" class = "productlist">상품목록</a></li>
       <li><a href="#" class="cartlist">장바구니목록</a></li>
       <li><a href="#" class="orderlist">주문목록</a></li>
    </ul>
</nav>