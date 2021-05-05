package pl.waw.psychologmaja.therapistrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.waw.psychologmaja.therapistrelief.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
