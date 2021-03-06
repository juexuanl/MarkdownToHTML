/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.drzoeylong.markdowntohtml.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.drzoeylong.markdowntohtml.entity.ConverterRequest;
import com.drzoeylong.markdowntohtml.entity.ConverterResponse;
import com.drzoeylong.markdowntohtml.logic.ConvertFailedException;
import com.drzoeylong.markdowntohtml.logic.MarkdownToHTMLConverter;

@Path("/converter")
public class ConverterService {
    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }


    @POST
    @Path("/json/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNotification(ConverterRequest request) {
    	ConverterResponse resp=new ConverterResponse();
    	MarkdownToHTMLConverter converter=new MarkdownToHTMLConverter();
    	try
    	{
	    	resp.result=converter.convert(request.data);
	    	resp.success=true;
	    	
    	}
    	catch(ConvertFailedException e)
    	{
    		resp.success=false;
    		resp.result=e.getMessage();
    	}
    	
        return Response.ok().entity(resp).build();
    }
}
