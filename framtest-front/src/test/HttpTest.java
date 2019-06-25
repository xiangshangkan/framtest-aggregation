import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName HttpTest
 * @Description Http请求
 * @Author zhouhui
 * @Date 2019/6/24 10:15
 * @Version 1.0
 */
public class HttpTest {

    public static PrintWriter out = null;
    public static BufferedReader in = null;
    public static String result = "";
    public static String url = "http://localhost:8080/testDemo/saveTeacher";


    public static  void main(String[] args){
        requestHttp();

    }

    public static void requestHttp() {
        try {
            URL readUrl = new URL(url);
            //打开和url之间的链接
            URLConnection conn = readUrl.openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //发送POST请求必须设置如下两行
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //获取URLConnection 对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数
            out.print("teacherName=zhouhui");
            //out.print("uid=" + uid +"&status=" + status + "&gender=" + gender + "&channel=" + u.getChannel());
            //flush输出流的缓冲
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null) {
                    out.close();
                } else if (in != null) {
                        in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
