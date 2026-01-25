using System;
using System.Windows.Forms;

namespace OrganizationManagement
{
    public partial class FrmOrganization : Form
    {
        public FrmOrganization()
        {
            InitializeComponent();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            string error = OrganizationValidator.Validate(
                txtOrgName.Text,
                txtPhone.Text,
                txtEmail.Text);

            if (error != null)
            {
                MessageBox.Show(error);
                return;
            }

            if (OrganizationService.IsOrgNameExists(txtOrgName.Text))
            {
                MessageBox.Show("Organization Name already exists");
                return;
            }

            OrganizationService.Insert(
                txtOrgName.Text,
                txtAddress.Text,
                txtPhone.Text,
                txtEmail.Text);

            MessageBox.Show("Save successfully");
            btnDirector.Enabled = true;
        }

        private void btnBack_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnDirector_Click(object sender, EventArgs e)
        {
            FrmDirector frm = new FrmDirector(txtOrgName.Text);
            frm.ShowDialog();
        }
    }
}
