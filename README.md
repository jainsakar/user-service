# user-service
Microservice that will have user management functionality for logistics manager

Database root username/password
root/root

#Learnings
1) For Bean validation use starter validator dependency instead of validation api or hibernate validator
    -> In Rest Controller add @Valid and in the Pojo add @NotNull, @NotBlank etc. annotations
2) For handling customized error in entire code base have a separate class with @RestControllerAdvice
3) For mapping/converting java data types with that of MySQL data types add implementation class for AbstractR2dbcConfiguration
    -> Mark the implementation class as @Configuration
    -> Override and mark @Bean over connectionFactory() method and specify your DB config
    -> Override getCustomConverters() for creating custom converters for data types - [ Reference - https://stackoverflow.com/questions/64351344/r2dbc-custom-converter ]
4) Add @EnableR2dbcAuditing on application class for enabling @CreatedDate and @LastModifiedDate
5) MySQL and Java Data type mapping for R2DBC SPI can be found at -[ Reference -  https://github.com/r2dbc/r2dbc-spi/blob/main/r2dbc-spec/src/main/asciidoc/data-types.adoc ]
6) JPA related schema management is not maintained by R2DBC so separate schema files has to be written to manage schemas
