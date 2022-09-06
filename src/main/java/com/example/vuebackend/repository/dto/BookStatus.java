package com.example.vuebackend.repository.dto;

import lombok.Data;

@Data
public class BookStatus {
    private int code;
    private String description;

    public BookStatus(int code){
        this.code = code;
        this.description = parseDescription(code);
    }

    public boolean isDisplayed(){
        return code == 200;
    }

    private String parseDescription(int code){
        switch(code){
            case 100:
                return "stopped selling";
            case 200:
                return "selling";
            case 300:
                return "sales hold";
            default:
                return "not supprted";
        }
        // switch(code){
        //     case 100 -> "stopped selling";
        //     case 200 -> "selling";
        //     case 300 -> "sales hold";
        //     default -> "not supprted";
        // }
    }
}
