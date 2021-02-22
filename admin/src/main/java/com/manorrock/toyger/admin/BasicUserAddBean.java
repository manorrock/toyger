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

import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * The CDI bean for /basic/user/add page.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "basicUserAddBean")
@RequestScoped
public class BasicUserAddBean {

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
     * Stores the password.
     */
    private String password1;

    /**
     * Stores the password again.
     */
    private String password2;

    /**
     * Stores the username.
     */
    private String username;

    /**
     * Get the password.
     *
     * @return the password.
     */
    public String getPassword1() {
        return password1;
    }

    /**
     * Get the password (again).
     *
     * @return the password (again).
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * Get the username.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Save the username.
     *
     * @return to the same page.
     */
    public String save() {
        if (password1 != null && password1.equals(password2)) {
            try {
                Process process = new ProcessBuilder()
                        .command("htpasswd",
                                "-Bb",
                                new File(application.getRootDirectory(), "passwd")
                                        .getAbsolutePath(),
                                username,
                                password1)
                        .start();

                int exit = process.waitFor();

                if (exit == 0) {
                    context.addMessage(null, new FacesMessage(SEVERITY_INFO,
                            "Sucessfully added user",
                            "Sucessfully added user"));
                } else {
                    throw new IOException();
                }

            } catch (IOException | InterruptedException e) {
                context.addMessage(null, new FacesMessage(SEVERITY_ERROR,
                        "An error occured while adding the user",
                        "An error occured while adding the user"));
            }
        } else {
            context.addMessage(null, new FacesMessage(SEVERITY_ERROR,
                    "Passwords do not match",
                    "Passwords do not match"));
        }
        return "";
    }

    /**
     * Set the password.
     *
     * @param password1 the password.
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    /**
     * Set the password (again).
     *
     * @param password2 the password (again).
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     * Set the username.
     *
     * @param username the username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
