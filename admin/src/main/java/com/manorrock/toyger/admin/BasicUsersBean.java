/*
 *  Copyright (c) 2002-2023, Manorrock.com. All Rights Reserved.
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
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The CDI bean for /basic/users page.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "basicUsersBean")
@RequestScoped
public class BasicUsersBean {
    
    /**
     * Stores the logger.
     */
    private static final Logger LOGGER = Logger.getLogger(BasicUsersBean.class.getPackageName());

    /**
     * Stores the application bean.
     */
    @Inject
    private ApplicationBean application;
    
    /**
     * Stores the list of users.
     */
    private List<String> users;

    /**
     * Get the list of users.
     *
     * @return the list of users.
     */
    public List<String> getUsers() {
        return users;
    }
    
    /**
     * Initialize the bean.
     */
    @PostConstruct
    public void initialize() {
        try {
            String rootDirectory = application.getRootDirectory();
            users = Files.readAllLines(Path.of(rootDirectory, "passwd"))
                    .stream()
                    .map(s -> s.substring(0, s.indexOf(":")))
                    .collect(Collectors.toList());
        } catch(IOException ioe) {
            LOGGER.warning("Unable to load BASIC passwd file");
            users = new ArrayList<>();
        }
    }
}
