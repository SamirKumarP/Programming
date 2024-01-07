
public class InnerClassPublic {

	public static void main(String[] args) {
		InnerClassPublic outerClass = new InnerClassPublic();
		InnerClassPublic.InnerClassPublic2 innerClass = outerClass.new InnerClassPublic2();
		InnerClassPublic1 classPublic2 = new InnerClassPublic1();
		InnerClassPublic3 classPublic3 = new InnerClassPublic3();

	}
	
	public class InnerClassPublic2 {
		
	}
	
	static class InnerClassPublic3 {
		
	}

}

class InnerClassPublic1 {
	
}
