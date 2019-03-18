package com.novoda.gradle.test

class GradleScriptTemplates {

    static String forJavaProject() {
        return """
            plugins { 
                id 'java-library'
                id 'com.novoda.bintray-release'
            }
            
            repositories {
                jcenter()
            }
            
            dependencies {
                implementation "junit:junit:4.12"
            }
            
            publish {
                userOrg = 'novoda'
                groupId = 'com.novoda'
                artifactId = 'test'
                publishVersion = '1.0'
                desc = 'description'
            }
               """.stripIndent()
    }

    static String forKotlinProject(String kotlinVersion = '1.3.21') {
        return """
            buildscript {
                ext.kotlin_version = '$kotlinVersion'
                repositories {
                    jcenter()
                }
                dependencies {
                    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:\$kotlin_version"
                }
            }

            plugins { 
                id 'com.novoda.bintray-release'
            }
            
            apply plugin: 'kotlin'
            
            project.sourceSets {
                main.java.srcDirs += 'src/main/kotlin'
            }
            
            repositories {
                jcenter()
            }
            
            dependencies {
                implementation "junit:junit:4.12"
                implementation "org.jetbrains.kotlin:kotlin-stdlib:\$kotlin_version"
            }
            
            task sourcesJar(type: Jar, dependsOn: classes) {
                from sourceSets.main.allSource
                classifier 'sources'
            }
            
            artifacts {
                archives sourcesJar
            }

            publish {
                userOrg = 'novoda'
                groupId = 'com.novoda'
                artifactId = 'test'
                publishVersion = '1.0'
                desc = 'description'
            }
               """.stripIndent()
    }

    static String forAndroidProject(String androidGradlePluginVersion = '3.0.0') {
        return """
            buildscript {
                repositories {
                    google()
                    jcenter()
                }
                dependencies {
                    classpath 'com.android.tools.build:gradle:$androidGradlePluginVersion'
                }
            }
            
            plugins {
                id 'com.novoda.bintray-release'
            }
            
            apply plugin: "com.android.library"
            
            android {
                compileSdkVersion 26
                buildToolsVersion "26.0.2"

                defaultConfig {
                    minSdkVersion 16
                    versionCode 1
                    versionName "0.0.1"
                }    
                
                lintOptions {
                   tasks.lint.enabled = false
                }
            }
            
            repositories {
                google()
                jcenter()
            }
            
            publish {
                userOrg = 'novoda'
                groupId = 'com.novoda'
                artifactId = 'test'
                publishVersion = '1.0'
                desc = 'description'
            }
               """.stripIndent()
    }

    static String forKotlinAndroidProject(String androidGradlePluginVersion = '3.0.0', String kotlinVersion = '1.3.21') {
        return """
            buildscript {
                ext.kotlin_version = '$kotlinVersion'
                repositories {
                    google()
                    jcenter()
                }
                dependencies {
                    classpath 'com.android.tools.build:gradle:$androidGradlePluginVersion'
                    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:\$kotlin_version"
                }
            }
            
            plugins {
                id 'com.novoda.bintray-release'
            }
            
            apply plugin: "com.android.library"
            apply plugin: "kotlin-android"
            
            android {
                compileSdkVersion 26
                buildToolsVersion "26.0.2"

                defaultConfig {
                    minSdkVersion 16
                    versionCode 1
                    versionName "0.0.1"
                }    
                
                lintOptions {
                   tasks.lint.enabled = false
                }
                
                sourceSets {
                    main.java.srcDirs += 'src/main/kotlin'
                }                
            }
            
            repositories {
                google()
                jcenter()
            }
            
            project.sourceSets {
                main.java.srcDirs += 'src/main/kotlin'
            }

            task sourcesJar(type: Jar) {
                from android.sourceSets.main.java.srcDirs
                classifier 'sources'
            }
            
            artifacts {
                archives sourcesJar
            }            

            publish {
                userOrg = 'novoda'
                groupId = 'com.novoda'
                artifactId = 'test'
                publishVersion = '1.0'
                desc = 'description'
            }
               """.stripIndent()
    }
}
