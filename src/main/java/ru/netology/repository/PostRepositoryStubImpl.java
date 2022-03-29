package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepositoryStubImpl implements PostRepository {

  private long counter = 0;
  private ConcurrentHashMap<Long, Post> collection = new ConcurrentHashMap<>();

  public ConcurrentHashMap<Long, Post> all() {
    return this.collection;
  }

  public Optional<Post> getById(long id) {
    if (collection.containsKey(id)) {
      return Optional.of(collection.get(id));
    } else {
      return Optional.empty();
    }
  }

  public synchronized Post save(Post post) {
    if (post.getId() == 0) {
      counter++;
      collection.put(counter, post);
    } else if (collection.containsKey(post.getId())) {
      collection.put(post.getId(), post);
    }
    return collection.get(post.getId());
  }

  public void removeById(long id) {
    collection.remove(id);
  }
}