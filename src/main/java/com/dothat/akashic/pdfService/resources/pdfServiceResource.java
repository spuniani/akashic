package com.dothat.akashic.pdfService.resources;

/**
 * Created by satya on 21/03/15.
 */

        //import com.google.common.base.Optional;
        //import com.codahale.metrics.annotation.Timed;
        import com.dothat.akashic.pdfService.core.*;
        import com.google.common.io.ByteStreams;
        //import org.glassfish.jersey.media.multipart.MultiPartFeature;
        import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
        import org.glassfish.jersey.media.multipart.FormDataParam;

        import javax.ws.rs.*;
        import javax.ws.rs.core.MediaType;
        import java.io.InputStream;
        //import java.nio.charset.Charset;
        import java.util.concurrent.atomic.AtomicLong;

@Path("/files")
@Produces(MediaType.APPLICATION_JSON)
public class pdfServiceResource {

    private final AtomicLong counter;

    public pdfServiceResource() {
        this.counter = new AtomicLong();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Item uploadItem(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {

        //read in the pdf data into a byte array
        try {
            byte[] uploadData = ByteStreams.toByteArray(uploadedInputStream);

            //create a key
            String itemKey = Long.toString(counter.incrementAndGet());

            //get the mimetype (TODO - this isnt giving me pdf?)
            String mimeType = fileDetail.getType();


            Item item = new Item(itemKey,mimeType,uploadData);

            return item;

        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }


        return null;

    }

}
