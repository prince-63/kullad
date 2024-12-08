package com.kullad.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.kullad.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfiguration {
}
