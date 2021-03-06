import com.google.gson.Gson;
import javafx.concurrent.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * 获取所有的任务信息并任务发布者信息
 *
 * @auther ZhengTianle
 * @Date: 18-6-6
 */
public class AllTaskInfoServlet extends HttpServlet {
    private String msg = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Service service = new Service();

        List<TaskInfo> list = new ArrayList<TaskInfo>();
        list = service.allTaskInfo();
        Gson gson = new Gson();
        String task = gson.toJson(list);
        Map<String,String> map = new HashMap<String,String>();

        //返回客户端任务信息
        PrintWriter out = response.getWriter();
        if(list.isEmpty()){
            map.put("tag","fail");
        }else{
            map.put("tag","success");
            map.put("info",task);
        }

        String message = gson.toJson(map);

        out.println(message);
        out.flush();
        out.close();


    }
}
