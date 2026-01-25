using System.Windows.Forms;

namespace OrganizationManagement
{
    public partial class FrmDirector : Form
    {
        public FrmDirector(string orgName)
        {
            InitializeComponent();
            lblOrgName.Text = orgName;
        }
    }
}
