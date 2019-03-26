    import java.util.*;  
    import java.sql.*;  
      
    public class EmpDao {  
      
        public static Connection getConnection(){  
            Connection con=null;  
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","123");  
            }catch(ClassNotFoundException | SQLException e){System.out.println(e);}  
            return con;  
        }  
        public static int save(Emp e){  
            int status=0;  
            try{  
                try (Connection con = EmpDao.getConnection()) {
                    PreparedStatement ps=con.prepareStatement(
                            "insert into user905(name,password,email,country) values (?,?,?,?)");
                    ps.setString(1,e.getName());
                    ps.setString(2,e.getPassword());
                    ps.setString(3,e.getEmail());
                    ps.setString(4,e.getCountry());
                    
                    status=ps.executeUpdate();
                }  
            }catch(SQLException ex){}  
              
            return status;  
        }  
        public static int update(Emp e){  
            int status=0;  
            try{  
                try (Connection con = EmpDao.getConnection()) {
                    PreparedStatement ps=con.prepareStatement(
                            "update user905 set name=?,password=?,email=?,country=? where id=?");
                    ps.setString(1,e.getName());
                    ps.setString(2,e.getPassword());
                    ps.setString(3,e.getEmail());
                    ps.setString(4,e.getCountry());
                    ps.setInt(5,e.getId());
                    
                    status=ps.executeUpdate();
                }  
            }catch(SQLException ex){}  
              
            return status;  
        }  
        public static int delete(int id){  
            int status=0;  
            try{  
                try (Connection con = EmpDao.getConnection()) {
                    PreparedStatement ps=con.prepareStatement("delete from user905 where id=?");
                    ps.setInt(1,id);
                    status=ps.executeUpdate();
                }  
            }catch(SQLException e){}  
              
            return status;  
        }  
        public static Emp getEmployeeById(int id){  
            Emp e=new Emp();  
              
            try{  
                try (Connection con = EmpDao.getConnection()) {
                    PreparedStatement ps=con.prepareStatement("select * from user905 where id=?");
                    ps.setInt(1,id);
                    ResultSet rs=ps.executeQuery();
                    if(rs.next()){
                        e.setId(rs.getInt(1));
                        e.setName(rs.getString(2));
                        e.setPassword(rs.getString(3));
                        e.setEmail(rs.getString(4));
                        e.setCountry(rs.getString(5));
                    }
                }  
            }catch(SQLException ex){}  
              
            return e;  
        }  
        public static List<Emp> getAllEmployees(){  
            List<Emp> list=new ArrayList<>();  
              
            try{  
                try (Connection con = EmpDao.getConnection()) {
                    PreparedStatement ps=con.prepareStatement("select * from user905");
                    ResultSet rs=ps.executeQuery();
                    while(rs.next()){
                        Emp e=new Emp();
                        e.setId(rs.getInt(1));
                        e.setName(rs.getString(2));
                        e.setPassword(rs.getString(3));
                        e.setEmail(rs.getString(4));
                        e.setCountry(rs.getString(5));
                        list.add(e);
                    }
                }  
            }catch(Exception e){}  
              
            return list;  
        }  
    }  