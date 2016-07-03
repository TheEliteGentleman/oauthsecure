/**
 * 
 */
package za.co.sindi.oauth.cdi;

import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Stereotype;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;

import za.co.sindi.commons.javaee.cdi.AnyAnnotationLiteral;
import za.co.sindi.commons.javaee.cdi.DefaultAnnotationLiteral;
import za.co.sindi.oauth.server.annotation.Service;


/**
 * @author Bienfait Sindi
 * @since 02 September 2014
 *
 */
public class ServiceExtension implements Extension {
	
	private final Map<Class<? extends za.co.sindi.oauth.server.service.Service>, Set<Annotation>> serviceClasses = new HashMap<>();
	
	void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager beanManager) {
		for (Entry<Class<? extends za.co.sindi.oauth.server.service.Service>, Set<Annotation>> entry : serviceClasses.entrySet()) {
			
			final Class<? extends za.co.sindi.oauth.server.service.Service> serviceType = entry.getKey();
			final Set<Annotation> qualifiers = entry.getValue();
//			AnnotatedType<? extends za.co.sindi.oauth.server.service.Service> at = beanManager.createAnnotatedType(serviceType);
			AnnotatedType<za.co.sindi.oauth.server.service.Service> at = beanManager.createAnnotatedType(za.co.sindi.oauth.server.service.Service.class);
			final InjectionTarget<za.co.sindi.oauth.server.service.Service> it = beanManager.createInjectionTarget(at);
			abd.addBean(new Bean<za.co.sindi.oauth.server.service.Service>() {

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.BeanAttributes#getTypes()
				 */
				@Override
				public Set<Type> getTypes() {
					// TODO Auto-generated method stub
					Set<Class<?>> interfaces = new HashSet<>();
					interfaces.add(serviceType);
					interfaces.addAll(Arrays.asList(serviceType.getInterfaces()));
					
					return new HashSet<Type>(interfaces);
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.BeanAttributes#getQualifiers()
				 */
				@Override
				public Set<Annotation> getQualifiers() {
					// TODO Auto-generated method stub
					return qualifiers;
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.BeanAttributes#getScope()
				 */
				@Override
				public Class<? extends Annotation> getScope() {
					// TODO Auto-generated method stub
					return RequestScoped.class;
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.BeanAttributes#getName()
				 */
				@Override
				public String getName() {
					// TODO Auto-generated method stub
					return serviceType.getName();
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.BeanAttributes#getStereotypes()
				 */
				@Override
				public Set<Class<? extends Annotation>> getStereotypes() {
					// TODO Auto-generated method stub
					Set<Class<? extends Annotation>> stereotypes = new HashSet<>();
					Annotation[] annotations = serviceType.getAnnotations();
					
					if (annotations != null) {
						for (Annotation annotation : annotations) {
							Class<? extends Annotation> annotationType = annotation.annotationType();
							if (annotationType.isAnnotationPresent(Stereotype.class)) {
								stereotypes.add(annotationType);
							}
						}
					}
					
					return stereotypes;
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.BeanAttributes#isAlternative()
				 */
				@Override
				public boolean isAlternative() {
					// TODO Auto-generated method stub
					return serviceType.isAnnotationPresent(Alternative.class);
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.context.spi.Contextual#create(javax.enterprise.context.spi.CreationalContext)
				 */
				@Override
				public za.co.sindi.oauth.server.service.Service create(CreationalContext<za.co.sindi.oauth.server.service.Service> creationalContext) {
					// TODO Auto-generated method stub
					za.co.sindi.oauth.server.service.Service instance = it.produce(creationalContext);
					it.inject(instance, creationalContext);
					it.postConstruct(instance);
					return instance;
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.context.spi.Contextual#destroy(java.lang.Object, javax.enterprise.context.spi.CreationalContext)
				 */
				@Override
				public void destroy(za.co.sindi.oauth.server.service.Service instance, CreationalContext<za.co.sindi.oauth.server.service.Service> creationalContext) {
					// TODO Auto-generated method stub
					it.preDestroy(instance);
					it.dispose(instance);
					creationalContext.release();
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.Bean#getBeanClass()
				 */
				@Override
				public Class<?> getBeanClass() {
					// TODO Auto-generated method stub
					return serviceType;
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.Bean#getInjectionPoints()
				 */
				@Override
				public Set<InjectionPoint> getInjectionPoints() {
					// TODO Auto-generated method stub
					return it.getInjectionPoints();
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.Bean#isNullable()
				 */
				@Override
				public boolean isNullable() {
					// TODO Auto-generated method stub
					return false; //As of CDI 1.1 this method is deprecated and can safely return a false.
				}
			});
		}
	}

	@SuppressWarnings("unchecked")
	<X> void processAnnotatedType(@WithAnnotations(Service.class) @Observes ProcessAnnotatedType<X> pat, BeanManager beanManager) {
		
		 /* wrap this to override the annotations of the class */
        final AnnotatedType<X> at = pat.getAnnotatedType();
        Class<X> serviceType = at.getJavaClass();
        if (isService(serviceType)) { 
//        	serviceClasses.add((Class<? extends za.co.sindi.oauth.server.service.Service>) serviceType);
        	serviceClasses.put((Class<? extends za.co.sindi.oauth.server.service.Service>) serviceType, getQualifiers(serviceType));
        } else if (serviceType.isAnnotationPresent(za.co.sindi.oauth.server.annotation.Service.class)) {
        	AnnotatedType<X> wrapped = new AnnotatedType<X>() {
        		
        		class ServiceAnnotationLiteral extends AnnotationLiteral<Service> implements Service {

					/**
					 * 
					 */
					private static final long serialVersionUID = 485522830572795761L;

					/* (non-Javadoc)
					 * @see za.co.sindi.oauth.server.annotation.Service#value()
					 */
					@Override
					public String value() {
						// TODO Auto-generated method stub
						Package pkg = at.getJavaClass().getPackage();
						String unqualifiedName = "";
						
						if (at.isAnnotationPresent(Service.class)) {
						    unqualifiedName = at.getAnnotation(Service.class).value();
						}
						
						if (unqualifiedName.isEmpty()) {
						    unqualifiedName = Introspector.decapitalize(at.getJavaClass().getSimpleName());
						}
						
						final String qualifiedName;
						if (pkg.isAnnotationPresent(Service.class)) {
						    qualifiedName = pkg.getAnnotation(Service.class).value() + '.' + unqualifiedName;
						} else {
						    qualifiedName = unqualifiedName;
						}
						
						return qualifiedName;
					}
        		}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.Annotated#getBaseType()
				 */
				@Override
				public Type getBaseType() {
					// TODO Auto-generated method stub
					return at.getBaseType();
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.Annotated#getTypeClosure()
				 */
				@Override
				public Set<Type> getTypeClosure() {
					// TODO Auto-generated method stub
					return at.getTypeClosure();
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.Annotated#getAnnotation(java.lang.Class)
				 */
				@Override
				public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
					// TODO Auto-generated method stub
					if (Service.class.equals(annotationType)) {
						return (T) new ServiceAnnotationLiteral();
					}
					
					return at.getAnnotation(annotationType);
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.Annotated#getAnnotations()
				 */
				@Override
				public Set<Annotation> getAnnotations() {
					// TODO Auto-generated method stub
					Set<Annotation> originalAnnotations = at.getAnnotations();
					Set<Annotation> returnAnnotations = new HashSet<>();
					
					boolean foundAnnotation = false;
					for (Annotation orignalAnnotation : originalAnnotations) {
						if (orignalAnnotation.annotationType().equals(Service.class)) {
							returnAnnotations.add(getAnnotation(Service.class));
							foundAnnotation = true;
						} else {
							returnAnnotations.add(orignalAnnotation);
						}
					}
					
					if (!foundAnnotation) {
						Package pkg = at.getJavaClass().getPackage();
						if (pkg.isAnnotationPresent(Service.class)) {
							returnAnnotations.add(getAnnotation(Service.class));
						}
					}
					
					return returnAnnotations;
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.Annotated#isAnnotationPresent(java.lang.Class)
				 */
				@Override
				public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
					// TODO Auto-generated method stub
					if (Service.class.equals(annotationType)) {
						return true;
					}
					
					return at.isAnnotationPresent(annotationType);
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.AnnotatedType#getJavaClass()
				 */
				@Override
				public Class<X> getJavaClass() {
					// TODO Auto-generated method stub
					return at.getJavaClass();
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.AnnotatedType#getConstructors()
				 */
				@Override
				public Set<AnnotatedConstructor<X>> getConstructors() {
					// TODO Auto-generated method stub
					return at.getConstructors();
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.AnnotatedType#getMethods()
				 */
				@Override
				public Set<AnnotatedMethod<? super X>> getMethods() {
					// TODO Auto-generated method stub
					return at.getMethods();
				}

				/* (non-Javadoc)
				 * @see javax.enterprise.inject.spi.AnnotatedType#getFields()
				 */
				@Override
				public Set<AnnotatedField<? super X>> getFields() {
					// TODO Auto-generated method stub
					return at.getFields();
				}
        	};
        	pat.setAnnotatedType(wrapped);
        }
	}
	
	private boolean isService(final Class<?> type) {
		return !type.isInterface() && za.co.sindi.oauth.server.service.Service.class.isAssignableFrom(type);
	}
	
	private Set<Annotation> getQualifiers(final Class<?> type) {
		Set<Annotation> qualifiers = new HashSet<>();
		Annotation[] annotations = type.getAnnotations();
		boolean containsAnyAnnotation = false;
		boolean containsDefaultAnnotation = false;
		
		if (annotations != null) {
			for (Annotation annotation : annotations) {
				if (annotation.equals(Default.class)) {
					containsDefaultAnnotation = true;
					qualifiers.add(annotation);
				}
				
				if (annotation.equals(Any.class)) {
					containsAnyAnnotation = true;
					qualifiers.add(annotation);
				}
				
				Class<? extends Annotation> annotationType = annotation.annotationType();
				if (annotationType.isAnnotationPresent(Qualifier.class)) {
					qualifiers.add(annotation);
				}
			}
		}
		
		if (qualifiers.isEmpty() || !containsDefaultAnnotation) {
			qualifiers.add(new DefaultAnnotationLiteral());
		}
		
		if (!containsAnyAnnotation) {
			qualifiers.add(new AnyAnnotationLiteral());
		}
		
		return qualifiers;
	}
}
