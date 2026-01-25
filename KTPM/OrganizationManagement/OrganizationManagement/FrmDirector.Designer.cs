namespace OrganizationManagement
{
    partial class FrmDirector
    {
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.Label lblOrgName;

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
                components.Dispose();
            base.Dispose(disposing);
        }

        private void InitializeComponent()
        {
            this.lblOrgName = new System.Windows.Forms.Label();
            this.SuspendLayout();

            // lblOrgName
            this.lblOrgName.AutoSize = true;
            this.lblOrgName.Location = new System.Drawing.Point(30, 30);
            this.lblOrgName.Name = "lblOrgName";
            this.lblOrgName.Size = new System.Drawing.Size(100, 16);
            this.lblOrgName.Text = "Organization";

            // FrmDirector
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(400, 200);
            this.Controls.Add(this.lblOrgName);
            this.Name = "FrmDirector";
            this.Text = "Director Management";
            this.ResumeLayout(false);
            this.PerformLayout();
        }
    }
}
