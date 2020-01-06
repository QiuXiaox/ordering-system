package my.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import my.util.ResponseVo;

@RequestMapping("/file")
public interface FileApi {
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/uploadFile")
	ResponseVo<String> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest req);
}
