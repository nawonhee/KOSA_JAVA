package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadDownloadController {
	@PostMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile f1, List<MultipartFile> f2) throws IOException {
		System.out.println(f1.getOriginalFilename()+":"+f1.getSize());
		
		if(f1 !=null && f1.getSize()>0) {
			File targetFile = new File("C:\\KOSA202307\\attaches", f1.getOriginalFilename()); //첫번째 인자는 저장될 디렉토리, 두번째 인자는 파일명. 이때 파일명은 가공해주어도 됨.
			FileCopyUtils.copy(f1.getBytes(), targetFile);
			
			for(MultipartFile mf : f2) {
				File targetFile2 = new File("C:\\KOSA202307\\attaches", mf.getOriginalFilename());
				FileCopyUtils.copy(f1.getBytes(), targetFile2);
				
				//----섬네일파일 만들기 START----
				int width=100;
				int height=100;				
				
				String thumbFileName = "t_" + f1.getOriginalFilename(); //섬네일파일명
				File thumbFile = new File("C:\\\\KOSA202307\\attaches" , thumbFileName);
				FileOutputStream thumbnailOS = new FileOutputStream(thumbFile);//출력스트림
				InputStream thumbnailIS = f1.getInputStream(); //첨부파일 입력스트림				
				Thumbnailator.createThumbnail(thumbnailIS, thumbnailOS, width, height);
				//-----섬네일파일 만들기 END------
				
			}
			return "upload OK";
		}else {
			return "upload FAIL";
		}
		
		
		
	}
	
	@GetMapping("/download")
	@ResponseBody
	public ResponseEntity<?> download() throws IOException { //응답에 관련된 객체들
		String existFileName="C:\\KOSA202307\\attaches\\t_test2_profile_F0001.PNG";
		HttpStatus status = HttpStatus.OK;
//		HttpStatus status = HttpStatus.NOT_FOUND;
//		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(existFileName, "UTF-8")); // 파일 크기 등등 다른 것도 설정하고 싶다면 header 더 추가해주면 된다
		
		
		File file  = new File(existFileName);
		String contentType = Files.probeContentType(file.toPath()); //파일의 형식
		headers.add(HttpHeaders.CONTENT_TYPE, contentType); //응답 형식
		
		headers.add(HttpHeaders.CONTENT_LENGTH, ""+file.length()); //응답 길이
		
		byte[] bArr=FileCopyUtils.copyToByteArray(file);
		ResponseEntity entity = new ResponseEntity<>(bArr, headers, status); //응답상태코드 
		return entity;
	}
}
