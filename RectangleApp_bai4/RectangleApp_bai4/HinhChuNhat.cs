using RectangleApp_bai4;
using System;

namespace RectangleApp_bai4
{
    public class HinhChuNhat
    {
        private Diem A;
        private Diem B;

        public HinhChuNhat(Diem A, Diem B)
        {
            this.A = A;
            this.B = B;
        }

        public int DienTich()
        {
            int width = Math.Abs(A.x - B.x);
            int height = Math.Abs(A.y - B.y);
            return width * height;
        }

        public bool GiaoNhau(HinhChuNhat h)
        {
            return !(A.x > h.B.x || B.x < h.A.x ||
                     A.y > h.B.y || B.y < h.A.y);
        }
    }
}
