package br.com.m4rc310.tv.services.graphql.control;

import java.util.Map;
import java.util.Optional;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import br.com.m4rc310.tv.services.MService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
public class ControlService extends MService {

	public static final String KEY_CONTROL_TV = "key_control_tv";
	

//	Queries
	@GraphQLQuery
	public String test() {
		return "OK";
	}
	

	@GraphQLQuery(name = "${number.version}", description = "${desc.number.version}")
	public String getVersion(@GraphQLContext ControlTV tv) {
		return getPomVersion();
	}

//	Mutations
	
	@GraphQLMutation(name = MUTATION$enable_tv, description = DESC$mutation_enable_tv)
	public ControlTV enableTV(
			@GraphQLArgument(name = INDICATOR$enable, description = DESC$indicator_enable)
			boolean enable) throws Exception {
		ControlTV tv = new ControlTV();
		tv.setEnable(enable);
		flux.callPublish(KEY_CONTROL_TV, tv);
		return tv;
	}

//	Subscriptions
	
	@GraphQLSubscription(name = SUBSCRIPTION$control_tv, description = DESC$subscription_control_tv)
	public Publisher<ControlTV> subControlTV() {
		ControlTV tv = new ControlTV();
		tv.setEnable(false);
		return flux.publish(ControlTV.class, KEY_CONTROL_TV, tv);
	}

	@Data
	public class ControlTV {
		@GraphQLQuery(name = INDICATOR$enable, description = DESC$indicator_enable)
		private boolean enable;
	}
	
	
	
	
	
}
