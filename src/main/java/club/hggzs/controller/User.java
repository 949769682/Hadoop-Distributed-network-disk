package club.hggzs.controller;


import club.hggzs.domain.Account;
import club.hggzs.domain.FileInfo;
import club.hggzs.service.AccountService;
import club.hggzs.service.impl.HDFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/User")
public class User {
    @Autowired
    private AccountService accountService;
    @Autowired
    private HDFS hdfs;
    @RequestMapping("/register")
    @ResponseBody
    public String register(HttpServletRequest request) {
        String username = request.getParameter("username-r");
        String password = request.getParameter("password-r");
        String mailbox = request.getParameter("mailbox");
        if(accountService.findByUsername(username) == null){
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(password);
            account.setMailbox(mailbox);
            accountService.insert(account);
            return "true";
        }
        return "false";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String  login(HttpServletRequest request){
        String username = request.getParameter("username-l");
        String password = request.getParameter("password-l");
        Account account = accountService.findByUsername(username);
        if(account != null && account.getPassword().equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            String path = request.getServletContext().getRealPath("/file/" + username+"/");
            session.setAttribute("path", path);
            return "true";
        }else{
            return "false";
        }
    }

    @RequestMapping("/FileList")
    public String FileList(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
            if(session != null){
                String username = (String) session.getAttribute("username");
                if(username != null) {
                    String path = "/" + username;
                    File file = new File((String) session.getAttribute("path"));
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    if (!hdfs.exists(path)) {
                        hdfs.mkdirs(path);
                    }
                    List<FileInfo> fileInfos;
                    fileInfos = hdfs.listFiles(path);
                    session.setAttribute("files", fileInfos);
                    return "FileList";
                }
            }
        return "login-not";

    }
    @RequestMapping("/upload")
    @ResponseBody
    public List<FileInfo> upload(HttpServletRequest request, MultipartFile f) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null){
            return null;
        }
        if(f != null){
            String username = (String) session.getAttribute("username");
            String path = (String) session.getAttribute("path");
            String filename = f.getOriginalFilename();
            File file = new File(path+filename);
            f.transferTo(file);
            if(file.exists()){
                hdfs.upload(path+filename,"/"+username);
                return hdfs.listFiles("/"+username);
            }
            return null;
        }
        return null;
    }
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, String filename) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session == null || filename == null || filename.equals("")){request.getRequestDispatcher("/pages/login-not").forward(request,response);}
        String username = (String) session.getAttribute("username");
        String path = "/" + username +"/" +filename;
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename,"utf-8"));
        try {
            InputStream in = hdfs.open(path);
            byte[] body = new byte[2048];
            int length = 0;
            OutputStream out = response.getOutputStream();
            while((length = in.read(body))!=-1){
                out.write(body,0,length);
                length += length;
            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(HttpServletRequest request,String filename) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null || filename == null || filename.equals("")){return false;}
        String username = (String) session.getAttribute("username");
        String filepath = "/" + username + "/" +filename;
        if(hdfs.exists(filepath)){
            return hdfs.delete(filepath);
        }
        return false;
    }
}
