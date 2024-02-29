package com.example.supplierresponseemulator.api_cloud.dto.nalog;

import com.example.supplierresponseemulator.api_cloud.dto.Inquiry;
import com.example.supplierresponseemulator.api_cloud.dto.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static com.example.supplierresponseemulator.api_cloud.utils.Constants.DATE_FORMAT;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelfEmplResponse extends Response {
    private int status;
    private boolean found;
    private String inn;
    private String date;
    private int NPD; // Статус НПД (1 - является налогоплательщиком на профессиональный доход, 0 - не является)
    private String message;
    private Inquiry inquiry;

    public static SelfEmplResponse createSelfEmplResponse(String inn, boolean isSelfEmpl) {

        if (isSelfEmpl) {
            String message = inn + " является плательщиком налога на профессиональный доход";
            return new SelfEmplResponse(200, true, inn, LocalDate.now().format(DATE_FORMAT), 1, message,
                    new Inquiry(0.6F, 734.50, 23, 1));
        }
        String message = inn + " не является плательщиком налога на профессиональный доход";
        return new SelfEmplResponse(200, true, inn, LocalDate.now().format(DATE_FORMAT), 0, message,
                new Inquiry(0.6F, 734.50, 23, 1));
    }
}
