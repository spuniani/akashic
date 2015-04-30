package com.dothat.akashic.pdfService.core;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by satya on 22/03/15.
 */
public class PDFFile {

    protected PDDocument document = null;

    public boolean load(byte[] bytes) throws IOException {
        InputStream is = new ByteArrayInputStream(bytes);
        PDFParser parser = new PDFParser(is);
        parser.parse();
        COSDocument cosDoc = parser.getDocument();
        this.document = new PDDocument(cosDoc);
        return true;
    }

    public String extractText() throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(this.document);

        return text;
    }

    public void close() throws IOException {
        if(this.document != null) {
            this.document.close();
        }
    }
}