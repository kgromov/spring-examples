package guru.springframework.services.profiling;

import com.javamex.classmexer.MemoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@Conditional(ClassmexerAgentCondition.class)
public class MemoryAnalyzer {

    public void printObjectMemoryUsage(Object o) {
        log.info("Memory Consumption");
        long bytes = MemoryUtil.deepMemoryUsageOf(o);
        log.info("Class = {}, bytes = {}", o.getClass().getName(), String.valueOf(bytes / 1024));
       /* CommandLineTable table = new CommandLineTable("Memory Consumption");
        table.setHeaders("Class", "Memory, KB");
        table.addRow(o.getClass().getName(), String.valueOf(bytes / 1024));
        table.printTable();*/
    }

    public void printObjectsMemoryUsage(Map objects) {
        log.info("Memory Consumption");
        objects.forEach((key, value) -> {
            long bytes = MemoryUtil.deepMemoryUsageOf(value);
            log.info("Class = {}, bytes = {}", key, bytes / 1024);
        });
      /*  CommandLineTable table = new CommandLineTable("Memory Consumption");
        table.setHeaders("Class", "Memory, bytes");
        objects.forEach((key, value) -> {
            long bytes = MemoryUtil.deepMemoryUsageOf(value);
            table.addRow(key.toString(), String.valueOf(bytes));
        });
        table.printTable();*/
    }
}
