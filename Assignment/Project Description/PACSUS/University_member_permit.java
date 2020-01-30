/* Generated by Together */

/**
 * For a description of University member permits, follow hyperlink to the Administration use case for issuing a new University member permit.
 * @url element://model:project::PACSUS/design:node:::8v22cgkuaa1f5-bdz00t
 */
public class University_member_permit extends Permit {
    /**
     * The date that this permit was issued. This information is required in case a member of the University leaves the University part way through the year and becomes eligible for a pro-rata rebate.
     * @label Issued on
     * @clientCardinality 1
     * @supplierCardinality 1 
     * @link aggregation
     * @directed
     */
    private Date lnkDate;
}