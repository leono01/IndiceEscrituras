dataSource {
    pooled = true
    username = "indice"
    password = "indice"
    driverClassName = "org.postgresql.Driver"
    dialect="org.hibernate.dialect.PostgreSQLDialect"
    dbCreate = "update"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            // one of 'create', 'create-drop', 'update', 'validate', ''
            dbCreate = "update"
            url = "jdbc:postgresql://192.168.1.114:5432/indice"
        }
    }
    
    production {
        dataSource {            
            //url = "jdbc:postgresql://postgres-notaweb.jelastic.servint.net/notaweb"
            url = "jdbc:postgresql://192.168.1.114:5432/indice"            
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }              
    }
}
