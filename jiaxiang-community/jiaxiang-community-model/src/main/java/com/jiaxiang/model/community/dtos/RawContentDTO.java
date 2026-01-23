package com.jiaxiang.model.community.dtos;

public class RawContentDTO {

    private String rawText; // 完全保留原始输入的文本

    public RawContentDTO(String rawText) {
        this.rawText = rawText;
    }

    // Getter & Setter
    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }
}
