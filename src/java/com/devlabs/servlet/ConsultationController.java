package com.devlabs.servlet;

import com.devlabs.model.Record;
import com.devlabs.service.ConsultationService;
import static com.devlabs.servlet.UserController.CONTENTTYPE_JSON;
import static com.devlabs.servlet.UserController.ENCODING_UTF8;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    
    private final ConsultationService consultationService = new ConsultationService();
    
    private final Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType(CONTENTTYPE_JSON);
        response.setCharacterEncoding(ENCODING_UTF8);
        
        PrintWriter writer = response.getWriter();
        
        writer.print(gson.toJson(this.consultationService.getRecords()));
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        
        String action = request.getParameter("action");
        
        if (action != null) {
            
            if (action.equalsIgnoreCase("save")) {
                
                try {
                    
                    String consultationSt = request.getParameter("consultation");
                    
                    JsonObject consultation = new JsonParser().parse(consultationSt).getAsJsonObject();
                    
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(consultation.get("date").getAsString());
                    
                    Record record = new Record(
                            this.consultationService.getMember(Long.parseLong(consultation.get("member").getAsString())),
                            this.consultationService.getProvider(Long.parseLong(consultation.get("provider").getAsString())),
                            this.consultationService.getService(Long.parseLong(consultation.get("service").getAsString())),
                            new Date(), date,
                            consultation.get("description").getAsString(),
                            consultation.get("comment").getAsString()
                    );
                    
                    if (this.consultationService.createConsultation(record)) {
                        writer.print(record);
                    } else {
                        writer.print(false);
                    }
                    
                } catch (ParseException ex) {
                    
                }
                
            }
            
        }
        
    }

    @Override
    public String getServletInfo() {
        return "ConsultationController Info";
    }
    
}
