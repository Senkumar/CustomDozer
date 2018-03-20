package customdozer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;


public class CustomMapper{
	/*
	 * Singleton instance of customMapperMap to hold instances of customMappers against the list of excludeMappings
	 */
	private static Map<List<?>, DozerBeanMapper> customMapperMap = new HashMap<List<?>, DozerBeanMapper>();

	
	/**
	 * Constructor for class : CustomMapper.java
	 * Made protected & empty to avoid instantiation
	 */
	protected CustomMapper(){} 
	
	/**
	 * Description: Single Class level - reuses the method which receives list of excludeMaps
	 * @param <S>
	 * @param <D>
	 * @param srcClass
	 * @param desClass
	 * @param excludeFields
	 * @return
	 * @return : DozerBeanMapper
	 */
	public static <S,D> DozerBeanMapper getCustomDozerMapper(final Class<S> srcClass, final Class<D> desClass, final List<String> excludeFields){
		ExcludeMapping<S,D> excludeMap = new ExcludeMapping<S,D>(srcClass, desClass, excludeFields);
		return getCustomDozerMapper(excludeMap);
	}
	
	/**
	 * Description: Single Class level - reuses the method which receives list of excludeMaps 
	 * @param <S>
	 * @param <D>
	 * @param excludeMap
	 * @return
	 * @return : DozerBeanMapper
	 */
	public static <S,D> DozerBeanMapper getCustomDozerMapper(final ExcludeMapping<S,D> excludeMap){
		List<ExcludeMapping<S,D>> excludeMaps = new ArrayList<ExcludeMapping<S,D>>();
		excludeMaps.add(excludeMap);
		return getCustomDozerMapper(excludeMaps);
	}
	
	/**
	 * Description: Multiple classes level
	 * @param excludeMaps
	 * @return
	 * @return : DozerBeanMapper
	 */
	public static <S,D> DozerBeanMapper getCustomDozerMapper(final List<ExcludeMapping<S,D>> excludeMaps){
		if(customMapperMap.containsKey(excludeMaps)){
			return customMapperMap.get(excludeMaps);
		}else{
			DozerBeanMapper mapper = new DozerBeanMapper();
			BeanMappingBuilder builder;
			for(ExcludeMapping<S,D> excludeMap : excludeMaps){
				builder = getBeanMappingBuilder(excludeMap.getSrcClass(),excludeMap.getDesClass(),excludeMap.getExcludeFields());
				mapper.addMapping(builder);
			}
			customMapperMap.put(excludeMaps, mapper);
			return mapper;
		}
	}
	
	/**
	 * Description: Mapping builder to exclude fields at specific class levels
	 * @param <S>
	 * @param <D>
	 * @param srcClass
	 * @param desClass
	 * @param excludeFields
	 * @return
	 * @return : BeanMappingBuilder
	 */
	private static <S,D> BeanMappingBuilder getBeanMappingBuilder(final Class<S> srcClass, final Class<D> desClass, final List<String> excludeFields){
		BeanMappingBuilder builder 	= new BeanMappingBuilder(){
			protected void configure(){
				TypeMappingBuilder mappingBuilder = mapping(srcClass, desClass,TypeMappingOptions.oneWay());
				if(null != excludeFields){
					for(String excluded : excludeFields){
						mappingBuilder.exclude(excluded);
					}
				}
			}
		};
		return builder;
	}
}