using Microsoft.Data.SqlClient;

namespace OrganizationManagement
{
    public class OrganizationService
    {
        public static bool IsOrgNameExists(string orgName)
        {
            using (SqlConnection conn = DBHelper.GetConnection())
            {
                string sql = @"SELECT COUNT(*) 
                               FROM ORGANIZATION 
                               WHERE LOWER(OrgName) = LOWER(@OrgName)";
                SqlCommand cmd = new SqlCommand(sql, conn);
                cmd.Parameters.AddWithValue("@OrgName", orgName);

                conn.Open();
                int count = (int)cmd.ExecuteScalar();
                return count > 0;
            }
        }

        public static void Insert(string orgName, string address, string phone, string email)
        {
            using (SqlConnection conn = DBHelper.GetConnection())
            {
                string sql = @"INSERT INTO ORGANIZATION
                               (OrgName, Address, Phone, Email)
                               VALUES (@OrgName, @Address, @Phone, @Email)";

                SqlCommand cmd = new SqlCommand(sql, conn);
                cmd.Parameters.AddWithValue("@OrgName", orgName);
                cmd.Parameters.AddWithValue("@Address", address);
                cmd.Parameters.AddWithValue("@Phone", phone);
                cmd.Parameters.AddWithValue("@Email", email);

                conn.Open();
                cmd.ExecuteNonQuery();
            }
        }
    }
}
