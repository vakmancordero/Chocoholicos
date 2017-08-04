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

    private Gson gson = new Gson();
    
    @Override
    public void init() throws ServletException {
        super.init();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        
        response.setContentType("application/json");
        
        String type = request.getParameter("type");
        
        if (type != null) {
            
            String word = request.getParameter("word");
            
            if (type.equalsIgnoreCase("member")) {
                
                List<Member> members = this.userService.searchMembers(word);
                
                System.out.println(members);
                
                writer.print("{\"members\": " + gson.toJson(members) + "}");
                
            } else if (type.equalsIgnoreCase("provider")) {
                
                List<Provider> providers = this.userService.searchProviders(word);
                
                System.out.println(providers);
                
                writer.print("{\"providers\": " + gson.toJson(providers) + "}");
                
            } else if (type.equalsIgnoreCase("service")) {
                
                List<Service> services = this.userService.searchServices(word);
                
                System.out.println(services);
                
                writer.print("{\"services\": " + gson.toJson(services) + "}");
                
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
                        
                    } else if (type.equalsIgnoreCase("provider")) {
                        
                        Provider provider = new Provider(
                                object.get("name").getAsString(), 
                                object.get("address").getAsString(), 
                                object.get("city").getAsString(), 
                                object.get("state").getAsString(), 
                                object.get("cp").getAsString(),
                                object.get("user").getAsString(),
                                object.get("password").getAsString()
                        );
                        
                        saved = this.userService.createProvider(provider);
                        
                    }
                    
                    writer.print(saved);
                    
                    break;
                    
                case "list":
                    
                    String typeUser = request.getParameter("type");
                    
                    if (typeUser.equalsIgnoreCase("member")) {
                        
                        writer.print(gson.toJson(this.userService.getMembers()));
                        
                    } else if (typeUser.equalsIgnoreCase("provider")) {
                        
                        writer.print(gson.toJson(this.userService.getProviders()));
                        
                    }
                    
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
