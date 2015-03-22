dataSource {
  pooled = true
  driverClassName = "com.mysql.jdbc.Driver"
  dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
  cache.use_second_level_cache = true
  cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
  cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
  singleSession = true // configure OSIV singleSession mode
  hibernate.show_sql=true
}



// environment specific settings
environments {
  development {
    dataSource {
        dbCreate = "create-drop"
        url = "jdbc:mysql://localhost/mychild"
        	username = "root"
			password = "123"
  }
  }
  test {
    dataSource {
      username = "gimme"
      password = "gimmepwd"
      pooled = true
      dbCreate = "create"
      driverClassName = "com.mysql.jdbc.Driver"
      //  url = "jdbc:mysql://aa1bzishuiat2fj.c3m5mgrxcx6j.ap-southeast-1.rds.amazonaws.com:3306/ebdb?user=fusion&password=plp247619"
        url="jdbc:mysql://gimmetestdb.cbj9zqqysdxf.ap-southeast-1.rds.amazonaws.com:3306/ebdb?user=gimme&password=gimmepwd"
        dialect = org.hibernate.dialect.MySQL5InnoDBDialect
        properties {
         validationQuery = "SELECT 1"
         testOnBorrow = true
         testOnReturn = true
         testWhileIdle = true
         timeBetweenEvictionRunsMillis = 1800000
         numTestsPerEvictionRun = 3
         minEvictableIdleTimeMillis = 1800000
         }
    }
  }
  production {
    dataSource {
      url = "jdbc:mysql://gimmetestdb.cbj9zqqysdxf.ap-southeast-1.rds.amazonaws.com:3306/ebdb?user=gimme&password=gimmepwd"
      username = "gimme"
      password = "gimmepwd"
      pooled = true
      dbCreate = "create"
      driverClassName = "com.mysql.jdbc.Driver"
      
      /*username = "fusion"
      password = "plp247619"
      pooled = true
      dbCreate = "update"
      driverClassName = "com.mysql.jdbc.Driver"
      url = "jdbc:mysql://aa1bzishuiat2fj.c3m5mgrxcx6j.ap-southeast-1.rds.amazonaws.com:3306/ebdb?user=fusion&password=plp247619"
      
      */  dialect = org.hibernate.dialect.MySQL5InnoDBDialect
        properties {
         validationQuery = "SELECT 1"
         testOnBorrow = true
         testOnReturn = true
         testWhileIdle = true
         timeBetweenEvictionRunsMillis = 1800000
         numTestsPerEvictionRun = 3
         minEvictableIdleTimeMillis = 1800000
        }
        
        
        
      
    }
  }
}
