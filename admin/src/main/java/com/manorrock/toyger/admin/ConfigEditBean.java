/*
 *  Copyright (c) 2002-2021, Manorrock.com. All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *      1. Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *
 *      2. Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer in the
 *         documentation and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 */
package com.manorrock.toyger.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * The CDI bean for /config/edit page.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "configEditBean")
@RequestScoped
public class ConfigEditBean {

    /**
     * Stores the application bean.
     */
    @Inject
    private ApplicationBean application;

    /**
     * Stores the Faces context.
     */
    @Inject
    private FacesContext context;

    /**
     * Stores the config.
     */
    private String config;

    /**
     * Get the config.
     *
     * @return the config.
     */
    public String getConfig() {
        return config;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {
        config = application.getConfigYml();
    }

    /**
     * Save the config.
     *
     * @return to the same page.
     */
    public String save() {
        try {
            Files.write(Paths.get(application.getRootDirectory(), "config.yml"),
                    config.getBytes());

            context.addMessage(null, new FacesMessage(SEVERITY_INFO,
                    "Sucessfully saved config.yml", 
                    "Sucessfully saved config.yml"));
            
        } catch (IOException ioe) {
            context.addMessage(null, new FacesMessage(SEVERITY_ERROR,
                    "An error occured while saving config.yml",
                    "An error occured while saving config.yml"));
        }
        return "";
    }

    /**
     * Set the config.
     *
     * @param config the config.
     */
    public void setConfig(String config) {
        this.config = config;
    }
}
