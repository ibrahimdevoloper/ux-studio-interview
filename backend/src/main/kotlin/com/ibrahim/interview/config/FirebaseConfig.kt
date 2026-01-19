package com.ibrahim.interview.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.IOException

@Configuration
class FirebaseConfig {
    @Value("\${GOOGLE_APPLICATION_CREDENTIALS}")
    private val firebaseConfigPath: String = ""

    @Bean
    @Throws(IOException::class)
    fun firebaseApp(): FirebaseApp? {
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(ClassPathResource(firebaseConfigPath).getInputStream()))
            .build()

        if (FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options)
        } else {
            return FirebaseApp.getInstance()
        }
    }
}
