using Microsoft.Data.SqlClient;

namespace OrganizationManagement
{
    public class DBHelper
    {
        public static SqlConnection GetConnection()
        {
            return new SqlConnection(
                "Server=.;Database=OrganizationDB;Trusted_Connection=True;TrustServerCertificate=True");
        }
    }
}
