package com.bs.proteo.git.commands;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by jgilniet on 29/06/2015.
 */

@Component
public class gitCommands implements CommandMarker {

    @CliCommand(value = "gt clone", help = "Clonar repositorio")
    public boolean cloneCommand(
            @CliOption(key = { "directory" }, mandatory = true) final String directory,
            @CliOption(key = { "url" }, mandatory = true) final String remoteUrl,
            @CliOption(key = { "user" }, mandatory = true) final String user,
            @CliOption(key = { "password"}, mandatory = true) final String password) {

        final File localpath = new File(directory);
        try {
            Git.cloneRepository()
                    .setURI(remoteUrl)
                    .setDirectory(localpath)
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(user, password))
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
