package com.example.supplierresponseemulator.api_cloud.dto.fssp;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Информация о запросе
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inquiry {
    private float price;
    private double balance;
    private int speed;
    private int attempts;
}
