package br.com.api.f1.services;

import br.com.api.f1.models.Driver;
import br.com.api.f1.models.DriversData;
import br.com.api.f1.models.Meta;
import br.com.api.f1.models.ResponseLinks;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class PopulateResponse {

    private PagedResourcesAssembler<Driver> pagedResourceAssembler;

    @Autowired
    public void setPagedResourceAssembler(final PagedResourcesAssembler<Driver> pagedResourceAssembler) {
        this.pagedResourceAssembler = pagedResourceAssembler;
    }

    @Value("${gateway.host:#{null}}")
    private String gatewayHost;

    @Value("${gateway.port:#{null}}")
    private String gatewayPort;

    private static final String HTTP = "http";

    public DriversData execute(final List<Driver> driversList, final int page, final int size, final int countCollection) {
        final Pageable pageable = PageRequest.of(page -1, size);

        final Page<Driver> pagedResult = new PageImpl<>(driversList, pageable, countCollection);

        return new DriversData(
            pagedResult.getContent(), buildResponseLinks(pagedResult), buildResponseMeta(pagedResult, Long.parseLong(String.valueOf(countCollection)))
        );
    }

    private ResponseLinks buildResponseLinks(final Page<Driver> pagedCollection) {
        final PagedModel<EntityModel<Driver>> pagedModel = pagedResourceAssembler.toModel(pagedCollection);
        return new ResponseLinks(
            getLink(pagedModel.getLink(IanaLinkRelations.SELF)),
            getLink(pagedModel.getLink(IanaLinkRelations.FIRST)),
            getLink(pagedModel.getNextLink()),
            getLink(pagedModel.getPreviousLink()),
            getLink(pagedModel.getLink(IanaLinkRelations.LAST))
        );
    }

    private String getLink(final Optional<Link> link) {
        String returnLink = link.map(Link::getHref).orElse("");
        if (link.isPresent()) {
            final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(link.get().getHref());
            if (NumberUtils.isParsable(gatewayPort)) {
                returnLink = builder.scheme(HTTP).host(gatewayHost).port(NumberUtils.toInt(gatewayPort)).build().toUriString();
            } else {
                returnLink = builder.scheme(HTTP).host(gatewayHost).build().toUriString();
            }
        }
        return returnLink;
    }

    private Meta buildResponseMeta(final Page<Driver> pagedCollection, final Long totalRecords) {
        return new Meta(totalRecords, pagedCollection.getTotalPages());
    }

}
