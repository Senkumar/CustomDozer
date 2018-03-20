package customdozer;

import java.util.List;

/**
 * Description: Class to bind the exclude multiple class level exclusions
 */
public final class ExcludeMapping <S,D>{
	private Class<S> srcClass;
	private Class<D> desClass;
	private List<String> excludeFields;
	

	/**
	 * Constructor for class : ExcludeMapping.java
	 * @param srcClass
	 * @param desClass
	 * @param excludeFields
	 */
	public ExcludeMapping(final Class<S> srcClass, final Class<D> desClass, final List<String> excludeFields){
		this.setDesClass(desClass);
		this.setSrcClass(srcClass);
		this.setExcludeFields(excludeFields);
	}

	public Class<S> getSrcClass(){
		return srcClass;
	}
	public void setSrcClass(Class<S> srcClass){
		this.srcClass = srcClass;
	}
	public Class<D> getDesClass(){
		return desClass;
	}
	public void setDesClass(Class<D> desClass){
		this.desClass = desClass;
	}
	public List<String> getExcludeFields(){
		return excludeFields;
	}
	public void setExcludeFields(List<String> excludeFields){
		this.excludeFields = excludeFields;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desClass == null) ? 0 : desClass.hashCode());
		result = prime * result + ((excludeFields == null) ? 0 : excludeFields.hashCode());
		result = prime * result + ((srcClass == null) ? 0 : srcClass.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcludeMapping other = (ExcludeMapping) obj;
		if (desClass == null) {
			if (other.desClass != null)
				return false;
		}else if (!desClass.equals(other.desClass))
			return false;
		if (excludeFields == null) {
			if (other.excludeFields != null)
				return false;
		}else if (!excludeFields.equals(other.excludeFields))
			return false;
		if (srcClass == null) {
			if (other.srcClass != null)
				return false;
		}else if (!srcClass.equals(other.srcClass))
			return false;
		return true;
	}
}