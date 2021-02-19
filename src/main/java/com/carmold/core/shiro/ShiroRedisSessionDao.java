package com.carmold.core.shiro;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import net.oschina.j2cache.J2Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * session
 *
 * @author 林新强
 * @date 2017年11月10日 下午2:26:45
 */
public class ShiroRedisSessionDao extends AbstractSessionDAO {

    private final static String REGION_SESSION = "blank_session";
    private CacheChannel cache = J2Cache.getChannel();

    public ShiroRedisSessionDao() {
        super();
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        cache.set(REGION_SESSION, session.getId().toString(), session);
    }

    @Override
    public void delete(Session session) {
        cache.evict(REGION_SESSION, session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Collection<String> keys = cache.keys(REGION_SESSION);
        Map<String, CacheObject> results = cache.get(REGION_SESSION, keys);
        Set<Session> sessions = new HashSet<Session>();
        results.forEach((k, v) -> {
            sessions.add((Session) v.getValue());
        });
        return sessions;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.generateSessionId(session);
        assignSessionId(session, sessionId);
        cache.set(REGION_SESSION, sessionId.toString(), session);
        return session.getId();

    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return (Session) cache.get(REGION_SESSION, sessionId.toString()).getValue();
    }

}
