package br.unipar.devbackend.trabalhobackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BackupService {

    @Value("${backup.folder}")
    private String backupFolder;

    @Value("${backup.pgDumpPath}")
    private String pgDumpPath;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Scheduled(cron = "0 */1 * * * *")
    public void executarBackup() {

        try {
            //criar a pasta se não existir
            File folder = new File(backupFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            //nome do arquivo
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String filePath = backupFolder + "/backup_" + timestamp + ".sql";

            //nome do banco a partir da URL
            String dbName = dbUrl.substring(dbUrl.lastIndexOf("/") + 1);

            //comando pg_dump
            String[] cmd = {
                    pgDumpPath,
                    "-h", "localhost",
                    "-p", "5432",
                    "-U", dbUser,
                    "-F", "c",
                    "-f", filePath,
                    dbName
            };

            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.environment().put("PGPASSWORD", "");
            pb.redirectErrorStream(true);
            pb.start();

            System.out.println("Backup criado: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
