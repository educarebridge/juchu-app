package com.example.webapp.form;

import lombok.Data;

@Data
public class TokuiMasterForm {
    
    // 得意先コード
    //private String tokuiCode;
    private String tokuiNo;
    
    // 得意先名
    private String tokuiName;

    // 必要に応じて電話番号や住所などのフィールドを追加してください
}
