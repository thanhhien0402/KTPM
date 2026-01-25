using System.Text.RegularExpressions;

public class OrganizationValidator
{
    public static string Validate(
        string orgName,
        string phone,
        string email)
    {
        if (string.IsNullOrWhiteSpace(orgName))
            return "Organization Name không được để trống";

        if (orgName.Length < 3 || orgName.Length > 255)
            return "Organization Name phải từ 3 đến 255 ký tự";

        if (!string.IsNullOrWhiteSpace(phone))
        {
            if (!Regex.IsMatch(phone, @"^\d{9,12}$"))
                return "Phone chỉ chứa số và dài 9–12 ký tự";
        }

        if (!string.IsNullOrWhiteSpace(email))
        {
            if (!Regex.IsMatch(email, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
                return "Email không đúng định dạng";
        }

        return null; // hợp lệ
    }
}
