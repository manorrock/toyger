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

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.util.logging.Level.FINE;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;
import java.util.logging.Logger;

/**
 * The one and only application bean.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationScoped
public class ApplicationBean {

    /**
     * Stores the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ApplicationBean.class.getPackageName());

    /**
     * Stores the root directory.
     */
    private String rootDirectory;

    /**
     * Constructor.
     */
    public ApplicationBean() {
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {
        rootDirectory = System.getenv("ROOT_DIRECTORY");
        if (rootDirectory == null) {
            rootDirectory = System.getProperty("rootDirectory",
                    System.getProperty("user.home") + File.separator
                    + ".manorrock" + File.separator + "toyger");
        }
        
        LOGGER.log(INFO, "Using root directory: {0}", rootDirectory);
        
        File directory = new File(rootDirectory);
        if (!directory.exists()) {
            LOGGER.log(FINE, "Creating root directory: {0}", rootDirectory);
            directory.mkdirs();
        }
    }
    
    /**
     * Get the config.yml content.
     * 
     * @return the config.yml content.
     */
    public String getConfigYml() {
        String config = "";
        try {
            config = Files.readString(Path.of(rootDirectory, "config.yml"));
        } catch (IOException ioe) {
            LOGGER.log(WARNING, "Unable to read config.yml", ioe);
        }
        return config;
    }

    /**
     * Get the root directory.
     *
     * @return the root directory.
     */
    public String getRootDirectory() {
        return rootDirectory;
    }
}
