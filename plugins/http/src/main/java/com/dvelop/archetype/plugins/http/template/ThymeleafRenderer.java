package com.dvelop.archetype.plugins.http.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
public class ThymeleafRenderer implements MessageBodyWriter<Object> {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final TemplateEngine templateEngine;

    @Inject
    AppInfo app;

    public ThymeleafRenderer() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("com/dvelop/archetype/plugins/http/ui/");
        templateResolver.setSuffix(".html");

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return 0;
    }

    @Override
    public void writeTo(Object o, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {

        String templateName = "";

        TemplateName annotation = type.getAnnotation(TemplateName.class);
        if (annotation != null) {
            templateName = annotation.value();
        } else {
            templateName = type.getSimpleName().toLowerCase();
            if(templateName.endsWith("dto")){
                templateName = templateName.substring(0, templateName.length()-3);
            }
        }

        try (OutputStreamWriter printWriter = new OutputStreamWriter(entityStream)) {
            Context context = new Context();
            context.setVariable("dto", o);
            context.setVariable("app", app);

            templateEngine.process(templateName, context, printWriter);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

}
