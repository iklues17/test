package net.chrisrichardson.getataxi.testutil.repositories;

import net.chrisrichardson.getataxi.repositories.Repository;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class MockRepositoryTestUtil {

  public static <T> CapturedArg<T> stubRespositoryAdd(Repository<T> repository, Class<T> clazz) {
    CapturedArg<T> result = new CapturedArg<>();

    Mockito.when(repository.add(Matchers.any(clazz), Matchers.any(List.class))).thenAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        result.arg = (T) invocation.getArguments()[0];
        return result.arg;
      }
    });
    Mockito.when(repository.add(Matchers.any(clazz))).thenAnswer(new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        result.arg = (T) invocation.getArguments()[0];
        return result.arg;
      }
    });
    return result;
  }

}
