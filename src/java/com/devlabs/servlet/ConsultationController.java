package com.devlabs.servlet;

import com.devlabs.model.Record;
import com.devlabs.service.ConsultationService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VakSF
 */
public class ConsultationController extends HttpServlet {
    
    private ConsultationService consultationService = new ConsultationService();
    
    private Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        PrintWriter writer = response.getWriter();
//        
//        writer.print(gson.toJson(this.consultationService.getRecords()));
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        
        String action = request.getParameter("action");
        
        if (action != null) {
            
            if (action.equalsIgnoreCase("save")) {
                
                String consultationSt = request.getParameter("consultation");
                JsonObject consultation = new JsonParser().parse(consultationSt).getAsJsonObject();
                
                Record record = new Record(
                        this.consultationService.getMember(Long.parseLong(consultation.get("member").getAsString())),
                        this.consultationService.getProvider(Long.parseLong(consultation.get("provider").getAsString())),
                        this.consultationService.getService(Long.parseLong(consultation.get("service").getAsString())),
                        new Date(), new Date(),
                        consultation.get("description").getAsString(),
                        consultation.get("comment").getAsString()
                );
                
                writer.print(this.consultationService.createConsultation(record));
                
            }
            
        }
        
    }

    @Override
    public String getServletInfo() {
        return "ConsultationController Info";
    }
    
}
