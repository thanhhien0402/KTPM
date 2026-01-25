namespace OrganizationManagement
{
    partial class FrmOrganization
    {
        private System.ComponentModel.IContainer components = null;

        private System.Windows.Forms.Label lblOrgName;
        private System.Windows.Forms.Label lblAddress;
        private System.Windows.Forms.Label lblPhone;
        private System.Windows.Forms.Label lblEmail;

        private System.Windows.Forms.TextBox txtOrgName;
        private System.Windows.Forms.TextBox txtAddress;
        private System.Windows.Forms.TextBox txtPhone;
        private System.Windows.Forms.TextBox txtEmail;

        private System.Windows.Forms.Button btnSave;
        private System.Windows.Forms.Button btnBack;
        private System.Windows.Forms.Button btnDirector;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        private void InitializeComponent()
        {
            this.lblOrgName = new System.Windows.Forms.Label();
            this.lblAddress = new System.Windows.Forms.Label();
            this.lblPhone = new System.Windows.Forms.Label();
            this.lblEmail = new System.Windows.Forms.Label();

            this.txtOrgName = new System.Windows.Forms.TextBox();
            this.txtAddress = new System.Windows.Forms.TextBox();
            this.txtPhone = new System.Windows.Forms.TextBox();
            this.txtEmail = new System.Windows.Forms.TextBox();

            this.btnSave = new System.Windows.Forms.Button();
            this.btnBack = new System.Windows.Forms.Button();
            this.btnDirector = new System.Windows.Forms.Button();

            this.SuspendLayout();

            // lblOrgName
            this.lblOrgName.AutoSize = true;
            this.lblOrgName.Location = new System.Drawing.Point(30, 30);
            this.lblOrgName.Text = "Organization Name (*)";

            // txtOrgName
            this.txtOrgName.Location = new System.Drawing.Point(180, 27);
            this.txtOrgName.Size = new System.Drawing.Size(250, 22);

            // lblAddress
            this.lblAddress.AutoSize = true;
            this.lblAddress.Location = new System.Drawing.Point(30, 70);
            this.lblAddress.Text = "Address";

            // txtAddress
            this.txtAddress.Location = new System.Drawing.Point(180, 67);
            this.txtAddress.Size = new System.Drawing.Size(250, 22);

            // lblPhone
            this.lblPhone.AutoSize = true;
            this.lblPhone.Location = new System.Drawing.Point(30, 110);
            this.lblPhone.Text = "Phone";

            // txtPhone
            this.txtPhone.Location = new System.Drawing.Point(180, 107);
            this.txtPhone.Size = new System.Drawing.Size(250, 22);

            // lblEmail
            this.lblEmail.AutoSize = true;
            this.lblEmail.Location = new System.Drawing.Point(30, 150);
            this.lblEmail.Text = "Email";

            // txtEmail
            this.txtEmail.Location = new System.Drawing.Point(180, 147);
            this.txtEmail.Size = new System.Drawing.Size(250, 22);

            // btnSave
            this.btnSave.Location = new System.Drawing.Point(80, 200);
            this.btnSave.Size = new System.Drawing.Size(90, 30);
            this.btnSave.Text = "Save";
            this.btnSave.Click += new System.EventHandler(this.btnSave_Click);

            // btnBack
            this.btnBack.Location = new System.Drawing.Point(190, 200);
            this.btnBack.Size = new System.Drawing.Size(90, 30);
            this.btnBack.Text = "Back";
            this.btnBack.Click += new System.EventHandler(this.btnBack_Click);

            // btnDirector
            this.btnDirector.Location = new System.Drawing.Point(300, 200);
            this.btnDirector.Size = new System.Drawing.Size(90, 30);
            this.btnDirector.Text = "Director";
            this.btnDirector.Enabled = false;
            this.btnDirector.Click += new System.EventHandler(this.btnDirector_Click);

            // FrmOrganization
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(480, 270);
            this.Controls.Add(this.lblOrgName);
            this.Controls.Add(this.txtOrgName);
            this.Controls.Add(this.lblAddress);
            this.Controls.Add(this.txtAddress);
            this.Controls.Add(this.lblPhone);
            this.Controls.Add(this.txtPhone);
            this.Controls.Add(this.lblEmail);
            this.Controls.Add(this.txtEmail);
            this.Controls.Add(this.btnSave);
            this.Controls.Add(this.btnBack);
            this.Controls.Add(this.btnDirector);
            this.Text = "Organization Management";

            this.ResumeLayout(false);
            this.PerformLayout();
        }
    }
}
