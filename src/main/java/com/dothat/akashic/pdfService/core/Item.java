package com.dothat.akashic.pdfService.core;

/**
 * Created by satya on 21/03/15.
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;

public class Item {
    private String itemKey;
    private String mimeType;
    private String extractedText;
    private byte[] uploadData;

    public Item() {
    }

    public Item(String itemKey, String mimeType, byte[] uploadData) {
        setItemKey(itemKey);
        setMimeType(mimeType);
        setUploadData(uploadData);
        try {
            pdfExtractText(uploadData);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Item(String itemKey, String mimeType, byte[] uploadData, String extractedText) {
        setItemKey(itemKey);
        setMimeType(mimeType);
        setUploadData(uploadData);
        setExtractedText(extractedText);
    }

    @JsonProperty
    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    @JsonIgnore
    public byte[] getUploadData() {
        return uploadData;
    }

    public void setUploadData(byte[] uploadData) {
        this.uploadData = uploadData;
    }

    @JsonProperty
    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    @JsonProperty
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    private void pdfExtractText(byte[] pdfSource) throws IOException {
        PDFFile pdfFile = new PDFFile();
        pdfFile.load(pdfSource);

        this.extractedText=pdfFile.extractText();
        pdfFile.close();

        //TODO if extracted text is gibberish, then fallback to OCR
        


    }
}