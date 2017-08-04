package com.devlabs.servlet;

import com.devlabs.model.Member;
import com.devlabs.model.Provider;
import com.devlabs.model.Service;
import com.devlabs.service.UserService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;

/**
 *
 * @author VakSF
 */
public class UserController extends HttpServlet {
    
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        
        response.setContentType("application/json");
        
        String type = request.getParameter("type");
        
        System.out.println("type = " + type);
        
        if (type != null) {
            
            String word = request.getParameter("word");
            
            if (type.equalsIgnoreCase("member")) {
                
                List<Member> members = this.userService.searchMembers(word);
                
                String membersJSON = "{\"members\": " + new Gson().toJson(members) + "}";
                
                writer.write(membersJSON);
                
            } else if (type.equalsIgnoreCase("provider")) {
                
                List<Provider> providers = this.userService.searchProviders(word);
                
                writer.write("{\"providers\": " + new Gson().toJson(providers) + "}");
                
            } else if (type.equalsIgnoreCase("service")) {
                
                List<Service> providers = this.userService.searchServices(word);
                
                writer.write("{\"services\": " + new Gson().toJson(providers) + "}");
                
            }
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        
        String action = request.getParameter("action");
        
        System.out.println(action);
        
        if (action != null) {
            
            switch (action.toLowerCase()) {
                
                case "save":
                    
                    String user = request.getParameter("user");
                    JsonObject object = new JsonParser().parse(user).getAsJsonObject();
                    
                    String type = object.get("type").getAsString();
                    
                    boolean saved = false;
                    
                    if (type.equalsIgnoreCase("member")) {
                        
                        Member member = new Member(
                                object.get("name").getAsString(), 
                                object.get("address").getAsString(), 
                                object.get("city").getAsString(), 
                                object.get("state").getAsString(), 
                                object.get("cp").getAsString()
                        );
                        
                        saved = this.userService.createMember(member);
                        
                    }
                    
                    writer.print(saved);
                    
                    break;
                    
                case "list":
                    
                    break;
                    
                case "delete":
                    
                    break;
                    
                default:
                    throw new AssertionError();
            }
            
        }
        
    }
    @Override
    public String getServletInfo() {
        return "UserController Info";
    }
    
}
