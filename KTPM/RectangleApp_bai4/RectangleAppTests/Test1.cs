using Microsoft.VisualStudio.TestTools.UnitTesting;
using RectangleApp_bai4;

namespace RectangleAppTests
{
    [TestClass]
    public class RectangleTest
    {
        [TestMethod]
        public void DienTich_HopLe()
        {
            Diem A = new Diem(0, 0);
            Diem B = new Diem(4, 3);
            HinhChuNhat h = new HinhChuNhat(A, B);

            int result = h.DienTich();

            Assert.AreEqual(12, result);
        }

        [TestMethod]
        public void GiaoNhau_CoGiao()
        {
            HinhChuNhat h1 = new HinhChuNhat(new Diem(0, 0), new Diem(4, 4));
            HinhChuNhat h2 = new HinhChuNhat(new Diem(2, 2), new Diem(6, 6));

            bool result = h1.GiaoNhau(h2);

            Assert.IsTrue(result);
        }

        [TestMethod]
        public void GiaoNhau_KhongGiao()
        {
            HinhChuNhat h1 = new HinhChuNhat(new Diem(0, 0), new Diem(2, 2));
            HinhChuNhat h2 = new HinhChuNhat(new Diem(3, 3), new Diem(5, 5));

            bool result = h1.GiaoNhau(h2);

            Assert.IsFalse(result);
        }
    }
}
