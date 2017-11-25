package cn.author.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.author.entity.User;
import cn.author.entity.UserExample;
import cn.author.entity.UserExample.Criteria;
import cn.author.mapper.UserMapper;


@Controller
public class uploadeController {
	@Value("#{prop.uploadPath}")
	private String uploadPath;	
	@Value("#{prop.imgUrl}")
	private String imgUrl;
//	public void setUploadPath(String uploadPath) {
//		System.out.println("this is setUploadPath method:"+uploadPath); 
//		this.uploadPath = uploadPath;
//	}
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value="upload.do",method = { RequestMethod.POST })
	@ResponseBody
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request) {
		logger.debug("成功进入控制层");//这里输出的文字  是为了可以在后台看到是否进入了这个方法  方便查错  
		//上传的目录  简单点 这里是E盘根目录  根据自己的需求改  想把上传的文件放在哪里  路径就写到哪里  
		//String path = "D:\\eclipse_workspace_base\\upload";
	  String originalFileName = file.getOriginalFilename();//获取上传的文件名字 日后可以根据文件名做相应的需改 例如自定义文件名 分析文件后缀名等等
	  String filenameExtension = StringUtils.getFilenameExtension(originalFileName);
	//新文件名，添加原始文件名后缀  
      String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));  
	  File targetFile = new File(uploadPath, newFileName);  //新建文件  
	  if (!targetFile.exists()) {    //判断文件的路径是否存在  
	    targetFile.mkdirs();  //如果文件不存在  在目录中创建文件夹   这里要注意mkdir()和mkdirs()的区别  
	  }
	  // 保存  
	  try {  
	    file.transferTo(targetFile); //传送  失败就抛异常  
	  } catch (Exception e) {  
	    e.printStackTrace();  
	  }  
	  System.out.println(imgUrl+newFileName);
	  return imgUrl+newFileName;   //看情况返回参数就OK了  
	}  

}
