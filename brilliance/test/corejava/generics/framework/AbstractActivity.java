package corejava.generics.framework;

/**
* @ClassName: AbstractActivity
* @Package corejava.generics.framework
* @Description: 活动抽象实现类
* @author Russell Xun Jiang
* @date 2016年12月27日 下午8:33:30
* 
* @param <T>	活动类型
*/

public class AbstractActivity<T> implements Operation<T>{

	@Override
	public T getActivity(String aid) {
		
		return null;
	}

	@Override
	public boolean addActivity(T a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exists(T a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
